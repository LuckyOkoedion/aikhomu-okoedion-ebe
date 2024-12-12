DO $$ BEGIN
  IF NOT pg_is_in_recovery() THEN
    -- Enable replication on primary DB
    ALTER SYSTEM SET wal_level = 'replica';
    ALTER SYSTEM SET max_wal_senders = 3;
    ALTER SYSTEM SET wal_keep_size = '64MB';
    SELECT pg_reload_conf();

    -- Create replication user if it doesn't exist
    IF NOT EXISTS (SELECT 1 FROM pg_roles WHERE rolname = 'replication_user') THEN
      CREATE ROLE replication_user WITH REPLICATION LOGIN PASSWORD 'replication_password';
    END IF;
  END IF;
END $$;

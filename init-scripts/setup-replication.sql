-- Enable replication on primary DB
ALTER SYSTEM SET wal_level = replica;
ALTER SYSTEM SET max_wal_senders = 3;
ALTER SYSTEM SET wal_keep_size = 64MB;
SELECT pg_reload_conf();

-- Create replication user
CREATE ROLE replication_user WITH REPLICATION LOGIN PASSWORD 'replication_password';

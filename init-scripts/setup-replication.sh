#!/bin/bash
set -e

# Wait for primary to become available
until pg_isready -h postgres-db-primary -p 5432; do
  echo "Waiting for primary database to be ready..."
  sleep 2
done

# Stop PostgreSQL to perform replication setup
pg_ctl stop -D "$PGDATA" -m fast

# Set the replication password using PGPASSWORD
export PGPASSWORD='replication_password'

# Perform a base backup from the primary server
pg_basebackup -h postgres-db-primary -D "$PGDATA" --wal-method=stream --username=postgres --write-recovery-conf

# Optional: Enable hot standby mode
echo "hot_standby = on" >> "$PGDATA/postgresql.conf"

# Start PostgreSQL
pg_ctl start -D "$PGDATA" -o "-c config_file=$PGDATA/postgresql.conf"

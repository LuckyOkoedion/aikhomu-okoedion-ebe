#!/bin/bash
# Set up replica to follow primary
pg_basebackup -h postgres-db-primary -D /var/lib/postgresql/data --wal-method=stream --username=replication_user --password=replication_password --write-recovery-conf
echo "standby_mode = 'on'" >> /var/lib/postgresql/data/recovery.conf
echo "primary_conninfo = 'host=postgres-db-primary port=5432 user=replication_user password=replication_password'" >> /var/lib/postgresql/data/recovery.conf

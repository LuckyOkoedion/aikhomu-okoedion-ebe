#!/bin/bash
set -e

if [ -f /tmp/pg_hba.conf ]; then
  echo "Copying pg_hba.conf for replica..."
  cp /tmp/pg_hba.conf "$PGDATA/pg_hba.conf"
  chown postgres:postgres "$PGDATA/pg_hba.conf"
fi

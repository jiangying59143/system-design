# It's not used anymore, just for reference.

# Base image
FROM mysql:5.7

# Copy starting scripts file
COPY ./config-server/set-sysn-and-create-db-table.sh /root/start.sh

# Run necessary services
CMD ["/bin/bash", "/root/start.sh"]

FROM maven:3-openjdk-18-slim

WORKDIR /apps

# COPY --chown=root:root ["./bakafinance", "/apps/"]

ENTRYPOINT [ "/bin/sh"]
# Step 1
FROM node:15.14.0-stretch-slim as builder

ENV NODE_HOME=/home/node
ENV SCODE_FRONTEND=./scode-frontend

COPY $SCODE_FRONTEND $NODE_HOME/

WORKDIR $NODE_HOME

RUN yarn install
RUN yarn run build

# Step 2
FROM caddy:2.3.0

COPY --from=builder /home/node/dist /app/

WORKDIR /app

EXPOSE 80
EXPOSE 443
EXPOSE 2015

CMD ["caddy", "run", "--config", "/etc/caddy/Caddyfile", "--adapter", "caddyfile"]

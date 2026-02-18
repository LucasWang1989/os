FROM node:20-alpine

# Image metadata
LABEL description="Dockerized online ordering system for customer." \
      maintainer="lucas.wang.1024@gmail.com"

WORKDIR /app/os/customer-web

# Only copy build output
COPY dist ./dist

# Lightweight static server (SPA mode)
RUN npm i -g serve

EXPOSE 3000
CMD ["serve", "-s", "dist", "-l", "3000"]

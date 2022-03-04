# build
FROM node:17-alpine as build
WORKDIR /app
COPY package*.json ./
ENV PATH="./node_modules/.bin:$PATH"
RUN npm install

COPY . ./
RUN npm run build

# production
FROM nginx:stable-alpine
COPY --from=build /app/build  /usr/share/nginx/html
COPY nginx/nginx.conf /etc/nginx/conf.d/default.conf

ENV PORT=80
CMD sed -i -e 's/$PORT/'"$PORT"'/g' /etc/nginx/conf.d/default.conf && nginx -g 'daemon off;'
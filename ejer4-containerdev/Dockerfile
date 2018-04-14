# our base image
FROM python:3-alpine

RUN apk update && apk upgrade && \
    apk add --no-cache bash git openssh

# run the application
CMD ["/bin/bash"]

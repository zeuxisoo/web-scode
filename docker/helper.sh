#!/bin/bash

function rebuild {
    docker-compose stop
    docker-compose down

    remove_container_all
    remove_image_none

    docker-compose up --build
}

function remove_image_none {
    IMAGES=$(docker images --filter "dangling=true" -q --no-trunc)

    if [[ $IMAGES == "" ]]; then
        echo "No dangling file"
    else
        docker rmi $IMAGES
    fi
}

function remove_container_all {
    docker rm $(docker ps -a -q)
}

COMMAND=${@:$OPTIND:1}

case $COMMAND in

    rebuild)
        rebuild
    ;;

    rm-image-none)
        remove_image_none
    ;;

    rm-container-all)
        remove_container_all
    ;;

    *)
        echo "- rm-image-none"
    ;;

esac

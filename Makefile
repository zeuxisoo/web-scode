CADDY_CONTAINER_ID=$(shell docker ps | grep caddy | awk '{print $$1;}')

all:
	@echo
	@echo "Command [Caddy]   : Description"
	@echo "----------------- : ------------"
	@echo "make caddy-reload : Reload the caddy with new config in docker service"
	@echo
	@echo
	@echo "Command [Docker]     : Description"
	@echo "-------------------- : ------------"
	@echo "make docker-rebuild  : Rebuild the docker service without pull base images"
	@echo "make docker-rm-all   : Remove all container"
	@echo "make docker-rmi-none : Remove dangling images"
	@echo

caddy-reload:
	@docker exec $(CADDY_CONTAINER_ID) caddy reload --config /etc/caddy/Caddyfile --adapter caddyfile

docker-rebuild:
	bash ./docker/helper.sh rebuild

docker-rm-all:
	bash ./docker/helper.sh rm-container-all

docker-rmi-none:
	bash ./docker/helper.sh rm-image-none

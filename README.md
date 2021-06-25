# Scode

A simple CRUD application for testing powered by spring boot and svelte

## Develop

1. Open the workspace file in vscode
2. Install the `spring boot tools`, `spring boot dashboard` and `svelte for vscode` extension
3. Run the backend program in the explorer spring boot section
4. Run the frontend in the terminal by

    ```
    cd scode-frontend
    yarn run dev
    ```

## Production

Run it in the docker service

1. Create the environment file

    ```
    cp .env.example .env
    ```

2. Edit the `.env` file
3. Start the docker services

    ```
    make up
    ```

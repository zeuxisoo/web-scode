import { defineConfig } from 'vite';
import svelte from '@sveltejs/vite-plugin-svelte';
import sveltePreprocess from 'svelte-preprocess';

// https://vitejs.dev/config/
export default defineConfig({
    server: {
        proxy: {
            '/api': {
                target: 'http://localhost:8080/',
                changeOrigin: true,
            }
        }
    },

    plugins: [
        svelte({
            preprocess: sveltePreprocess({
                defaults: {
                    style: "postcss"
                },
                postcss: true,
                scss: {
                    includePaths: ['src', 'node_modules'],
                },
            })
        })
    ]
});

import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      "/customer": {
        target: "http://localhost",
        changeOrigin: true,
      },
      "/api": {
        target: "http://localhost",
        changeOrigin: true,
      },
    },
  },
})

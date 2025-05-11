import { defineConfig } from 'astro/config';
import Icons from 'unplugin-icons/vite';

import tailwindcss from '@tailwindcss/vite';

import react from '@astrojs/react';

// https://astro.build/config
export default defineConfig({
  integrations: [
    react()
  ],
  vite: {
    plugins: [
      Icons({
        compiler: 'astro',
        autoInstall: true
      }),
      tailwindcss()
    ]
  }
});
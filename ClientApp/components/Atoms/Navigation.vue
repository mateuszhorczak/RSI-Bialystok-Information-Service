<script setup lang="ts">
import type { NavigationMenuItem } from '@nuxt/ui'

const items = ref<NavigationMenuItem[]>([
  {
    label: 'Strona Główna',
    icon: 'i-mdi-home',
    to: '/',
  },
  {
    label: 'Wydarzenia',
    icon: 'i-mdi-calendar',
    to: '/events',
  },
  {
    label: 'Dziś',
    icon: 'i-mdi-calendar-star',
    to: '/events/today',
  },
  {
    label: 'Znajdź',
    icon: 'i-mdi-magnify',
    to: '/events/find',
  },
  {
    label: 'Stwórz',
    icon: 'i-mdi-plus-circle',
    to: '/events/new',
  },
])
</script>

<template>
  <div class="flex">
    <UNavigationMenu
      :items="items"
      highlight
      class="mx-auto"
    />
  </div>
</template>

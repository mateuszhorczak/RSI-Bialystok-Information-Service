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
    label: 'Wyszukaj wydarzenia',
    icon: 'i-mdi-magnify',
    children: [{
      label: 'po nazwie',
      icon: 'i-mdi-text-box-search',
      to: '/events/find',
    },
    {
      label: 'po dacie',
      icon: 'i-mdi-calendar-filter',
      to: '/events/find/by-date',
    },
    {
      label: 'po tygodniu',
      icon: 'i-mdi-calendar-week',
      to: '/events/find/by-week',
    }],
  },
  {
    label: 'Stwórz',
    icon: 'i-mdi-plus-circle',
    to: '/events/new',
  },
  {
    label: 'Eksportuj do pdf',
    icon: 'i-mdi-file-pdf',
    to: '/events/pdf',
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

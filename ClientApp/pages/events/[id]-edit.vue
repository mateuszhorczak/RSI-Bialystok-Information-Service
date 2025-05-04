<script setup lang="ts">
const route = useRoute()
const id = route.params.id
</script>

<template>
  <p>{{ id }} edit</p>
</template>

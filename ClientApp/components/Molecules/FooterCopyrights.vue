<script setup lang="ts">
const currentYear = new Date().getFullYear()

const lightText = 'font-light text-sm text-white leading-tight'
const boldText = 'font-bold text-sm text-white leading-tight mx-1'
</script>

<template>
  <div class="flex">
    <AtomsParagraph
      :class="lightText"
      class="text-"
    >
      Copyright ©{{ currentYear }}
    </AtomsParagraph>
    <AtomsParagraph :class="boldText">
      Mateusz Horczak, Anita Jurkowska
    </AtomsParagraph>
    <AtomsParagraph :class="lightText">
      wszystkie prawa zastrzeżone.
    </AtomsParagraph>
  </div>
</template>

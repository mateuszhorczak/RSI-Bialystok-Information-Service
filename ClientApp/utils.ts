import type { CalendarDate } from '@internationalized/date'

export function getWeekNumber(date: CalendarDate) {
  const d = new Date(date.year, date.month - 1, date.day)
  d.setHours(0, 0, 0, 0)
  d.setDate(d.getDate() + 4 - (d.getDay() || 7))
  const yearStart = new Date(d.getFullYear(), 0, 1)
  // @ts-expect-error silence error
  return Math.ceil((((d - yearStart) / 86400000) + 1) / 7)
}

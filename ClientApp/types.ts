export interface Event {
  name: string
  type: string
  date: string
  week: number
  month: number
  year: number
  description: string
}

export interface BaseEvent {
  name: string
  type: string
  date: string
  description: string
}

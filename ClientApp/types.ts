export interface Event {
  id: number
  name: string
  type: string
  date: string
  week: number
  month: number
  year: number
  description: string
}

export interface BaseEvent {
  id: number
  name: string
  type: string
  date: string
  description: string
}

import { configureStore } from '@reduxjs/toolkit'
import filterReducer from './slices/filterSlice'
import countReducer from './slices/counterSlice'

export const store = configureStore({
  reducer: {
    countReducer,
    filterReducer
  },
})
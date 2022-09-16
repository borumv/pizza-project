import { createSlice } from '@reduxjs/toolkit'

const initialState = {
  activeCategory: 0,
  sort: {name:'популярности по возрастанию',
         sortProperty: 'rating'},
}

export const filterSlice = createSlice({
  name:'filter',
  initialState,
  reducers:{
    setActiveCategory: (state, action) =>{state.activeCategory = action.payload},
    setOrder:(state, action) => {state.sort = action.payload}
  }

})

export const { setActiveCategory, setOrder} = filterSlice.actions

export default filterSlice.reducer
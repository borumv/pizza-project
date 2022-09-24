import { createSlice } from '@reduxjs/toolkit'

const initialState = {
  activeCategory: 0,
  sort: {name:'популярности по возрастанию',
         sortProperty: 'rating'},
  searchValue: ''
}

export const filterSlice = createSlice({
  name:'filter',
  initialState,
  reducers:{
    setActiveCategory: (state, action) =>{state.activeCategory = action.payload},
    setOrder:(state, action) => {state.sort = action.payload},
    setSearchValue: (state, action)=>{state.searchValue = action.payload},
    setFilters: (state, action) => {
      console.log('dispatch', action)
      state.activeCategory = action.payload.activeCategory
      state.sort.name = action.payload.sortProperty.name
      state.sort.sortProperty = action.payload.sortProperty.sortProperty
//      state.searchValue = action.searchValue
    }
  }

})

export const { setActiveCategory, setOrder,setSearchValue, setFilters} = filterSlice.actions

export default filterSlice.reducer
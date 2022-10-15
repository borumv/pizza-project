import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { Sort, SortProperty } from '../../components/Sorting';
import { RootState } from '../store';

type IFilterSliceState = {
  activeCategory: number;
  sort: SortProperty;
  searchValue: string;
};
const initialState: IFilterSliceState = {
  activeCategory: 0,
  sort: {
    name: 'популярности по возрастанию',
    sortProperty: Sort.RATING_ASC,
  },
  searchValue: '',
};

export const filterSlice = createSlice({
  name: 'filter',
  initialState,
  reducers: {
    setActiveCategory: (state, action: PayloadAction<number>) => {
      state.activeCategory = action.payload;
    },
    setOrder: (state, action: PayloadAction<SortProperty>) => {
      console.log('action - ', action);
      state.sort = action.payload;
    },
    setSearchValue: (state, action) => {
      state.searchValue = action.payload;
    },
    setFilters: (state, action) => {
      console.log('dispatch', action);
      state.activeCategory = action.payload.activeCategory;
      state.sort = action.payload.sortProperty;
    },
  },
});

export const activeCategorySelector = (state: RootState) => state.filterReducer.activeCategory;
export const sortSelector = (state: RootState) => state.filterReducer;
export const sortingValuesSelector = (state: RootState) => state.filterReducer.sort;

export const { setActiveCategory, setOrder, setSearchValue, setFilters } = filterSlice.actions;

export default filterSlice.reducer;

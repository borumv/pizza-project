import { createSlice } from '@reduxjs/toolkit';

const initialState = {
  items: [],
  totalCost: 0,
  totalCount: 0,
};

export const cartSlice = createSlice({
  name: 'cart',
  initialState,
  reducers: {
    addItem: (state, action) => {
      const haveItem = (item, action) => {
        return (
          item.title === action.title && item.size === action.size && item.type === action.type
        );
      };
      if (state.items.some((item) => haveItem(item, action.payload))) {
        state.items.forEach((item) => {
          if (haveItem(item, action.payload)) {
            item.count = item.count + 1;
          }
        });
      } else {
        state.items.push(action.payload);
      }
      state.totalCount = state.items.reduce((prev, next) => {
        return prev + next.count;
      }, 0);
      state.totalCost = state.items.reduce((o1, o2) => {
        return o1 + o2.cost * o2.count;
      }, 0);
    },
    clearCart: (state) => {
      return (state = {
        items: [],
        totalCost: 0,
        totalCount: 0,
      });
    },
    increment: (state, action) => {
      const item = state.items.find(
        (obj) =>
          action.payload.data.id === obj.id &&
          action.payload.data.type === obj.type &&
          action.payload.data.size === obj.size,
      );
      item.count = item.count + 1;
      state.totalCount = state.items.reduce((prev, next) => {
        return prev + next.count;
      }, 0);
      state.totalCost = state.items.reduce((o1, o2) => {
        return o1 + o2.cost * o2.count;
      }, 0);
    },
    decrement: (state, action) => {
      const item = state.items.find(
        (obj) =>
          action.payload.data.id === obj.id &&
          action.payload.data.type === obj.type &&
          action.payload.data.size === obj.size,
      );
      item.count = item.count - 1;
      state.totalCount = state.items.reduce((prev, next) => {
        return prev + next.count;
      }, 0);
      state.totalCost = state.items.reduce((o1, o2) => {
        return o1 + o2.cost * o2.count;
      }, 0);
    },

    removeItem: (state, action) => {
      console.log('action - ', action);
      state.items = state.items.filter((obj) => {
        return !(
          action.payload.data.id === obj.id &&
          action.payload.data.type === obj.type &&
          action.payload.data.size === obj.size
        );
      });
      state.totalCount = state.items.reduce((prev, next) => {
        return prev + next.count;
      }, 0);
      state.totalCost = state.items.reduce((o1, o2) => {
        return o1 + o2.cost * o2.count;
      }, 0);
    },
  },
});

export const { addItem, clearCart, increment, decrement, removeItem } = cartSlice.actions;

export default cartSlice.reducer;

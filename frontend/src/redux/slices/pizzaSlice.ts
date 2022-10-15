import { createSlice } from '@reduxjs/toolkit';
import { createAsyncThunk } from '@reduxjs/toolkit';
import axios from 'axios';
import { error } from 'console';
import { ISearchPizzaParams } from '../../pages/Home';

export enum Status {
  LOADING = 'loading',
  COMPLETE = 'complete',
  ERROR = 'ERROR',
}
type ISizeAndCost = {
  size: number;
  cost: number;
};
type PageInfo = {
  number: number;
  totalResult: number;
  totalPages: number;
};

export type IPizza = {
  id: number;
  imageUrl: string[];
  title: string;
  types: number[];
  sizeAndCost: ISizeAndCost[];
  category: number[];
  rating: number;
};
type ResponseData = {
  pageInfo: PageInfo;
  itemsList: IPizza[];
};

type IFirstResponseData = {
  types: string[];
  categories: string[];
  items: ResponseData;
};

type IState = {
  data: {
    types: string[];
    categories: string[];
    items: ResponseData;
  };

  status: Status;
};

const initialState: IState = {
  data: {
    types: [],
    categories: [],
    items: {
      pageInfo: {
        number: 0,
        totalResult: 0,
        totalPages: 0,
      },
      itemsList: [],
    },
  },

  status: Status.LOADING,
};

export const fetchPizza = createAsyncThunk<IFirstResponseData | ResponseData, ISearchPizzaParams>(
  'pizza/fetchPizzas',
  async (params) => {
    const { category, pageNumber, limit, orderingValue, orderingType, searchValues, pathReq } =
      params;
    const response = await axios.get<IFirstResponseData | ResponseData>(
      `http://localhost:8080/api/pizza/model/${pathReq}_pizzes_page=${pageNumber}limit=${limit}?${category}${orderingValue}${orderingType}${searchValues}`,
    );
    return response.data;
  },
);

export const pizzaSlice = createSlice({
  name: 'pizza',
  initialState,
  reducers: {
    addItem: (state, action) => {
      state.data = action.payload;
    },
    setPage: (state, action) => {
      state.data.items.pageInfo.number = action.payload;
    },
    // dicrementPage: (state, action) => {
    //   state.data.items.pageInfo.number = action.payload;
    // },
  },
  extraReducers: (builder) => {
    // Add reducers for additional action types here, and handle loading state as needed
    builder.addCase(fetchPizza.pending, (state) => {
      state.status = Status.LOADING;
    });
    builder.addCase(fetchPizza.fulfilled, (state, action) => {
      const fetchStatus = action.meta.arg.pathReq;

      if (fetchStatus === 'all') {
        let payload = action.payload as unknown as IFirstResponseData;
        window.localStorage.setItem('categories', JSON.stringify(payload.categories));
        window.localStorage.setItem('types', JSON.stringify(payload.types));
        state.data = action.payload as IFirstResponseData;
      } else {
        if (window.localStorage.getItem('categories') && window.localStorage.getItem('types')) {
          state.data.categories = JSON.parse(window.localStorage.getItem('categories') as string);
          state.data.types = JSON.parse(window.localStorage.getItem('types') as string);
        }
        state.data.items = action.payload as unknown as ResponseData;
      }
      state.status = Status.COMPLETE;
    });

    builder.addCase(fetchPizza.rejected, (state) => {
      state.data = {
        types: [],
        categories: [],
        items: {
          pageInfo: {
            number: 0,
            totalResult: 0,
            totalPages: 0,
          },
          itemsList: [],
        },
      };

      state.status = Status.ERROR;
    });
  },
});

export const { addItem, setPage } = pizzaSlice.actions;

export default pizzaSlice.reducer;

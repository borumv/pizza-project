import React, { useCallback } from 'react';
import { useSelector } from 'react-redux';
import { setFilters } from '../redux/slices/filterSlice';
import { fetchPizza, IPizza, Status } from '../redux/slices/pizzaSlice';
import { setActiveCategory } from '../redux/slices/filterSlice';
import qs from 'qs';
import { useNavigate } from 'react-router-dom';

import { Categories } from '../components/Categories';
import { Sorting, listValues } from '../components/Sorting';
import Sceleton from '../components/PizzaBlock/Sceleton';
import { sortingValuesSelector } from '../redux/slices/filterSlice';
import Paginator from '../components/Paginator';
import { useAppDispatch } from '../redux/hook';
import { RootState } from '../redux/store';
import { Pizza } from '../components/PizzaBlock';

type IPopUp = MouseEvent & {
  path: Node[];
};

enum PathReq {
  ONLY = 'only',
  ALL = 'all',
}
export type ISearchPizzaParams = {
  category: string;
  pageNumber: string;
  limit: string;
  orderingValue: string;
  orderingType: string;
  searchValues: string;
  pathReq: PathReq;
};

const Home = () => {
  const activeCategory: number = useSelector(
    (state: RootState) => state.filterReducer.activeCategory,
  );
  const sortingValue = useSelector(sortingValuesSelector);
  const searchValue = useSelector((state: RootState) => state.filterReducer.searchValue);
  const { types } = useSelector((state: RootState) => state.pizzaReducer.data);

  const { itemsList, pageInfo } = useSelector((state: RootState) => state.pizzaReducer.data.items);
  const status = useSelector((state: RootState) => state.pizzaReducer.status);
  const categories = ['Все'].concat(
    useSelector((state: RootState) => state.pizzaReducer.data.categories),
  );
  //const categories = (useSelector((state: RootState) => state.pizzaReducer.items.categories));
  const dispatch = useAppDispatch();
  const isSearch = React.useRef(false);
  const isMount = React.useRef(false);
  const isFetching = React.useRef(false);
  const sortingPopup = React.useRef<HTMLDivElement>();
  const [openState, setOpenState] = React.useState(false);
  const [pageNumber, setPageNumber] = React.useState(0);
  const limit = 4;
  const navigate = useNavigate();

  const onChangeCategory = useCallback((index: number) => {
    dispatch(setActiveCategory(index));
  }, []);

  const fetchPizzes = async () => {
    console.log(activeCategory, sortingValue, searchValue);
    const category: string = activeCategory > 0 ? `category_id=${activeCategory}&` : '';
    const orderingValue: string = `orderingValue=${sortingValue.sortProperty.replace('-', '')}`;
    const orderingType: string = `&ordering_type=${
      sortingValue.sortProperty.includes('-') ? 'desc' : 'asc'
    }`;
    const searchValues: string = `&search_value=${searchValue}`;
    if (window.localStorage.getItem('categories') || window.localStorage.getItem('type')) {
      isFetching.current = true;
    }

    dispatch(
      fetchPizza({
        category,
        pageNumber: String(pageNumber),
        limit: String(limit),
        orderingValue,
        orderingType,
        searchValues,
        pathReq: isFetching.current ? PathReq.ONLY : PathReq.ALL,
      }),
    );
    isFetching.current = true;
  };

  //click outside Popup logic
  React.useEffect(() => {
    const handleCLickOutSide = (event: MouseEvent) => {
      const _event = event as IPopUp;
      if (sortingPopup.current && !_event.path.includes(sortingPopup.current)) {
        setOpenState(false);
      }
    };
    document.body.addEventListener('click', handleCLickOutSide);

    return () => {
      document.body.removeEventListener('click', handleCLickOutSide);
    };
  }, []);

  //creating queryString with windowLoaction
  React.useEffect(() => {
    if (window.location.search) {
      const pathProperties = qs.parse(window.location.search.substring(1));
      const sortProperty = listValues.find(
        (obj) => pathProperties.sortProperty === obj.sortProperty,
      );
      console.log('sortProperty - ', sortProperty);
      dispatch(
        setFilters({
          ...pathProperties,
          sortProperty: sortProperty || listValues[0],
        }),
      );
      isSearch.current = true;
    }
  }, []);

  React.useEffect(() => {
    if (!isSearch.current) {
      fetchPizzes();
    }

    isSearch.current = false;
  }, [activeCategory, searchValue, pageNumber, sortingValue]);

  React.useEffect(() => {
    if (isMount.current) {
      const stringQuery = qs.stringify({
        activeCategory,
        sortProperty: sortingValue,
      });
      navigate(`?${stringQuery}`);
    }
    isMount.current = true;
  }, [activeCategory, searchValue, sortingValue]);

  return (
    <>
      <div className="content__top">
        <Categories onchangeCategory={onChangeCategory} categories={categories} />
        <Sorting sortingRef={sortingPopup} openState={openState} setOpenState={setOpenState} />
      </div>
      <h2 className="content__title">Все пиццы</h2>
      {status === Status.ERROR ? (
        <div>error</div>
      ) : (
        <div className="content__items">
          {status === 'loading'
            ? [...new Array(6)].map((_, index) => {
                return <Sceleton key={index} />;
              })
            : itemsList.map((item: any) => {
                return <Pizza key={item.id} {...item} types={types} />;
              })}
        </div>
      )}
      <Paginator
        pageNumber={pageNumber}
        setPageNumber={setPageNumber}
        countElements={pageInfo.totalResult / limit}
        activeCategory={activeCategory}
      />
    </>
  );
};
export default Home;

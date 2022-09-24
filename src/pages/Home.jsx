import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { setActiveCategory, setFilters } from '../redux/slices/filterSlice';
import axios from 'axios';
import qs from 'qs';
import { useNavigate } from 'react-router-dom';

import Pizza from '../components/PizzaBlock';
import Categories from '../components/Categories';
import Sorting, { listValues } from '../components/Sorting';
import Sceleton from '../components/PizzaBlock/Sceleton';

import Paginator from '../components/Paginator';

const Home = () => {
  const activeCategory = useSelector((state) => state.filterReducer.activeCategory);
  const sortingValue = useSelector((state) => state.filterReducer.sort);
  const searchValue = useSelector((state) => state.filterReducer.searchValue);
  const dispatch = useDispatch();
  const isSearch = React.useRef(false);
  const isMount = React.useRef(false);
  const sortingPopup = React.useRef();
  const [openState, setOpenState] = React.useState(false);

  const [pageNumber, setPageNumber] = React.useState(0);
  const [isLoading, setIsLoading] = React.useState(false);
  const [{ pizzaModelList, types, count }, setItems] = React.useState({
    categories: [],
    pizzaModelList: [],
    types: [],
    count: 0,
  });
  const limit = 4;
  const navigate = useNavigate();
  const categories = ['Все', 'Мясные', 'Вегетарианская', 'Гриль', 'Острые', 'Закрытые'];

  React.useEffect(() => {
    const handleCLickOutSide = (event) => {
      if (!event.path.includes(sortingPopup.current)) {
        setOpenState(false);
      }
    };
    document.body.addEventListener('click', handleCLickOutSide);

    return () => {
      document.body.removeEventListener('click', handleCLickOutSide);
    };
  }, []);

  React.useEffect(() => {
    if (window.location.search) {
      const pathProperties = qs.parse(window.location.search.substring(1));
      const sortProperty = listValues.find(
        (obj) => pathProperties.sortProperty === obj.sortProperty,
      );
      dispatch(
        setFilters({
          ...pathProperties,
          sortProperty,
        }),
      );
      isSearch.current = true;
    }
  }, []);

  const fetchPizzes = () => {
    setIsLoading(false);

    const category = activeCategory > 0 ? `category_id=${activeCategory}&` : '';
    const orderingValue = `orderingValue=${sortingValue.sortProperty.replace('-', '')}`;
    const orderingType = `&ordering_type=${
      sortingValue.sortProperty.includes('-') ? 'desc' : 'asc'
    }`;
    const searchValues = `&search_value=${searchValue}`;
    axios
      .get(
        `http://localhost:8080/api/pizza/model/all_pizzes_page=${pageNumber}limit=${limit}?${category}${orderingValue}${orderingType}${searchValues}`,
      )
      .then((item) => {
        setItems(item.data);
        setIsLoading(true);
      })
      .catch((err) => console.log('catched! ', err));
  };

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
        sortProperty: sortingValue.sortProperty,
      });
      navigate(`?${stringQuery}`);
    }
    isMount.current = true;
  }, [activeCategory, searchValue, sortingValue]);

  return (
    <>
      <div className="content__top">
        <Categories categories={categories} activeCategory={activeCategory} />
        <Sorting sortingRef={sortingPopup} openState={openState} setOpenState={setOpenState} />
      </div>
      <h2 className="content__title">Все пиццы</h2>
      <div className="content__items">
        {isLoading
          ? pizzaModelList.map((item, index) => {
              return <Pizza key={item.id} {...item} types={types} />;
            })
          : [...new Array(6)].map((_, index) => {
              return <Sceleton key={index} />;
            })}
      </div>
      <Paginator
        pageNumber={pageNumber}
        setPageNumber={setPageNumber}
        countElements={count / limit}
        activeCategory={activeCategory}
      />
    </>
  );
};
export default Home;

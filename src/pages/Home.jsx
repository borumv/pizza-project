import React from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { setActiveCategory } from '../redux/slices/filterSlice'

import Pizza from '../components/PizzaBlock';
import Categories from '../components/Categories';
import Sorting from '../components/Sorting';
import Sceleton from '../components/PizzaBlock/Sceleton'

import { SearchContext } from '../App';
import Paginator from '../components/Paginator';


const Home = () =>{

  const activeCategory = useSelector((state) => state.filterReducer.activeCategory)
  const sortingValue = useSelector((state) => state.filterReducer.sort)
  const dispatch = useDispatch()
  const categories = ['Все', 'Мясные', 'Вегетарианская', 'Гриль', 'Острые', 'Закрытые']

  const [pageNumber, setPageNumber] = React.useState(0);
  const [isLoading, setIsLoading] = React.useState(false);
  const [{ pizzaModelList, types, count}, setItems] = React.useState({categories:[], pizzaModelList:[], types:[], count:0});
  const {searchValue, } = React.useContext(SearchContext)
  
  const limit = 3;
  const category = activeCategory > 0 ? `category_id=${activeCategory}&`:'';
  const orderingValue = `orderingValue=${sortingValue.sortProperty.replace('-','')}`;
  const orderingType = `&ordering_type=${sortingValue.sortProperty.includes('-')? 'desc':'asc'}`;
  const searchValues = `&search_value=${searchValue}`

  
  const onChangeCategory = (index) => {
    dispatch(setActiveCategory(index))
  }

  React.useEffect(() =>{
    setIsLoading(false)
    fetch(`http://localhost:8080/api/pizza/model/all_pizzes_page=${pageNumber}limit=${limit}?${category}${orderingValue}${orderingType}${searchValues}`)
    .then((item) => { return item.json()})
    .then((jsonitems) =>{
                setItems(jsonitems)
                setIsLoading(true)})

    .catch(err => console.log("catched! ",  err))
  },[activeCategory, searchValue, pageNumber, sortingValue, category, orderingValue, orderingType, searchValues]);


  return(
    <>
    <div className="content__top">
              <Categories categories = {categories} activeCategory={activeCategory} setActiveCategory ={onChangeCategory}/>
              <Sorting />
            </div>
            <h2 className="content__title">Все пиццы</h2>
            <div className="content__items">  
              {isLoading
              ? pizzaModelList.map(( item, index) => {return (<Pizza key={index} {...item}  types = {types}/>)})
              : [...new Array(6)].map((_, index) => {return (<Sceleton key={index}/>)})}                 
   </div>
    <Paginator pageNumber = {pageNumber} setPageNumber = {setPageNumber} countElements = {count / limit} activeCategory ={activeCategory}/>
   </>
  )
}
export default Home;
import React from 'react'

import Pizza from '../components/PizzaBlock';
import Categories from '../components/Categories';
import Sorting from '../components/Sorting';
import Sceleton from '../components/PizzaBlock/Sceleton'

import { SearchContext } from '../App';

const Home = () =>{
  const [isLoading, setIsLoading] = React.useState(false);
  const [{categories, pizzaModelList, types}, setItems] = React.useState({categories:[], pizzaModelList:[], types:[]});
  const [activeCategory, setActiveCategory] = React.useState(0)
  const {searchValue, } = React.useContext(SearchContext)
  const sortingList = [
    {name:'популярности по возрастанию', sortProperty: 'rating'},
    {name:'цене по возрастанию', sortProperty: 'price'}, 
    {name:'алфавиту по возрастанию', sortProperty: 'title'},
    {name:'популярности по убыванию', sortProperty: '-rating'},
    {name:'цене по убыванию', sortProperty: '-price'}, 
    {name:'алфавиту по убыванию', sortProperty: '-title'}
  ]
  const [sortingValue, setSortingValue] = React.useState({name:'популярности', sortProperty: 'rating'});
  const category = activeCategory > 0 ? `category_id=${activeCategory + 1}&`:''
  const orderingValue = `orderingValue=${sortingValue.sortProperty.replace('-','')}`;
  const orderingType = `&ordering_type=${sortingValue.sortProperty.includes('-')? 'desc':'asc'}`;
  const searchValues = `&search_value=${searchValue}`


  React.useEffect(() =>{
    setIsLoading(false)
    fetch(`http://localhost:8080/api/pizza/model/all_pizzes?${category}${orderingValue}${orderingType}${searchValues}`)
    .then((item) => { return item.json()})
    .then((jsonitems) =>{
                setItems(jsonitems)
                setIsLoading(true)})

    .catch(err => console.log("catched! ",  err))
  },[activeCategory, sortingValue, searchValue]);


  return(
    <>
    <div className="content__top">
              <Categories categories = {categories} activeCategory={activeCategory} setActiveCategory ={setActiveCategory}/>
              <Sorting listValues = {sortingList} changeValue = {sortingValue} setChangeValue= {setSortingValue}/>
            </div>
            <h2 className="content__title">Все пиццы</h2>
            <div className="content__items">  
              {isLoading
              ? pizzaModelList.map(( item, index) => {return (<Pizza key={index} {...item}  types = {types}/>)})
              : [...new Array(6)].map((_, index) => {return (<Sceleton key={index}/>)})}                 
   </div>
   </>
  )
}
export default Home;
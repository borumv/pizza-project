import React from 'react'

import Pizza from '../components/PizzaBlock';
import Categories from '../components/Categories';
import Sorting from '../components/Sorting';
import Sceleton from '../components/PizzaBlock/Sceleton'


const Home = () =>{
  const [isLoading, setIsLoading] = React.useState(false);
  const [{categories, pizzaModelList, types}, setItems] = React.useState({categories:[], pizzaModelList:[], types:[]});
  const [activeCategory, setActiveCategory] = React.useState(0)

  React.useEffect(() =>{
    fetch('http://localhost:8080/api/pizza/model/all_data')
    .then((item) => { return item.json()})
    .then((jsonitems) =>{
                setItems(jsonitems)
                setIsLoading(true)})

    .catch(err => console.log("catched! ",  err))
  },[]);


  return(
    <>
    <div className="content__top">
              <Categories categories = {categories} activeCategory={activeCategory} setActiveCategory ={setActiveCategory}/>
              <Sorting/>
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
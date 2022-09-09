import React from 'react'

export default function Categories({categories, activeCategory, setActiveCategory}) {
  // const [activeIndex, setActiveIndex] = React.useState(0)
  //const categories = ['Мясные', 'Вегетарианская', 'Гриль', 'Острые', 'Закрытые']
  console.log(activeCategory)
  return(
    <>
    <div className="categories">
      <ul>
      {
        categories.map((item, index) =>{
          return (<li key={index} onClick={() => {setActiveCategory(index)}} className={activeCategory === index ? 'active' : ''}>{item}</li>)
        })
      }
      </ul>
  </div>
  </>
  )
  
}
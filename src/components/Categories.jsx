import React from 'react'

export default function Categories({categories, activeCategory, setActiveCategory}) {

  console.log(categories +  " " + activeCategory)
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
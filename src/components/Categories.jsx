import React from 'react'

export default function Categories({categories}) {
  const [activeIndex, setActiveIndex] = React.useState(0)
  //const categories = ['Мясные', 'Вегетарианская', 'Гриль', 'Острые', 'Закрытые']
  return(
    <>
    <div className="categories">
      <ul>
      {
        categories.map((item, index) =>{
          return (<li key={index} onClick={() => {setActiveIndex(index)}} className={activeIndex === index ? 'active' : ''}>{item}</li>)
        })
      }
      </ul>
  </div>
  </>
  )
  
}
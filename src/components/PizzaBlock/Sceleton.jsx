import React from "react"
import ContentLoader from "react-content-loader"

const Sceleton = (props) => (
  <div className="pizza-block">
  <ContentLoader 
    speed={2}
    width={280}
    height={475}
    viewBox="0 0 280 475"
    backgroundColor="#f3f3f3"
    foregroundColor="#ecebeb"
    {...props}
  >
   <circle cx="134" cy="136" r="125" /> 
    <rect x="0" y="279" rx="10" ry="10" width="270" height="23" /> 
    <rect x="0" y="326" rx="10" ry="10" width="270" height="88" /> 
    <rect x="0" y="436" rx="10" ry="10" width="90" height="30" /> 
    <rect x="125" y="427" rx="24" ry="24" width="122" height="35" />
  </ContentLoader>
  </div>
)

export default Sceleton
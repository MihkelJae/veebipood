import { useEffect, useState } from "react";
import { Button } from "react-bootstrap";
import { Product } from "../models/Product";

function HomePage() {
  const[products, setProducts] = useState<Product[]>([]);
  const[categories, setCategories] = useState<Product[]>([]);
  const size = 2;
  // const page = 1;
  const[activePage, setActivePage] = useState(1);
  const[pages, setPages] = useState<number[]>([]);
  const[activeCategoryId, setActiveCategoryId] = useState(0);
  

  useEffect(() => {
      fetch(`http://localhost:8080/public-products?categoryId=${activeCategoryId}&size=${size}&page=${activePage-1}`)
      .then(res => res.json())
      .then(json => {
        setProducts(json.content)
        const pagesArray = [];
        for (let page = 1; page <= json.totalPages; page++) {
          pagesArray.push(page)
        }
        setPages(pagesArray);
      });
    }, [activePage, activeCategoryId]);

    useEffect(() => {
      fetch("http://localhost:8080/categories")
      .then(res => res.json())
      .then(json => setCategories(json));
    }, []);

    function filterBycategory(categoryId: number) {
      setActiveCategoryId(categoryId);
      setActivePage(1);
      // fetch("http://localhost:8080/products-by-category?categoryId=" + categoryId)
      // .then(res => res.json())
      // .then(json => {
      //   setProducts(json.content)
      //   const pagesArray = [];
      //   for (let page = 1; page <= json.totalPages; page++) {
      //     pagesArray.push(page)
      //   }
      //   setPages(pagesArray);
      // });
    }

    function addToCart(product: Product){
      console.log(product);
    }

    function changePage(newPage: number) {
      setActivePage(newPage);
    }
  
  return (
    <div>

      <div>
        <button onClick={() => filterBycategory(0)}>All categories</button>
        {categories.map(category => <button onClick={() => filterBycategory(category.id)}>
          {category.name}
          </button>)}
      </div>
      <div>
      {products.map(product => 
      <div key={product.id}>
        <img src={product.image} alt="Product image"/>
        <div>{product.name}: </div>
        <div>{product.price}</div>
        {/* <div>{product.image}</div> */}
        <Button variant="outlined" onClick={() => addToCart(product)}>ADD TO CART</Button>
    </div>
      )}
    </div>

    {
      pages.map(page => <button key={page} onClick={() => changePage(page)}>{page}</button>)
    }

    </div>
    
  )
}

export default HomePage

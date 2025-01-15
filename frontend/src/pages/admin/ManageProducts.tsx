import { useEffect, useState } from "react";
import { Product } from "../../models/Product";
import Button from "@mui/material/Button";



function ManageProducts() {
    const[products, setProducts] = useState<Product[]>([]);
    const[message, setMessage] = useState("");

    useEffect(() => {
        fetch("http://localhost:8080/products")
        .then(res => res.json())
        .then(json => setProducts(json));
      }, []);

      function deleteProduct(product: Product) {
        fetch("http://localhost:8080/products/" + product.id, {
          method: "DELETE"
        })
        .then(res => res.json())
        .then(json => {
            if (json.timestamp && json.status && json.error) {
                setMessage(json.error);
              } else {
            setProducts(json)
        }
    });
}


  return (
    <div>
        <div>{message}</div>
    <div>{products.map(product => 
      <div>
        <span>{product.name}: </span>
        <span>{product.price} €</span>
        <Button variant="outlined" onClick={() => deleteProduct(product)}>x</Button>
        </div>
      )}
      </div>
    </div>
  )
}

export default ManageProducts
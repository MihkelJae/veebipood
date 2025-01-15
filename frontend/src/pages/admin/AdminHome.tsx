import Button from 'react-bootstrap/Button';
import { Link } from 'react-router-dom';

function AdminHome() {
  return (
    <div>
        <Button as={Link} to="/admin/products">Manage products</Button>
        <Button as={Link} to="/admin/categories">Manage categories</Button>
        <Button as={Link} to="/admin/add-product">Add Product</Button>
        <Button variant="warning">Warning</Button>
    </div>
  )
}

export default AdminHome
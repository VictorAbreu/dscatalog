import { ReactComponent as MainImage } from 'assets/images/main-image.svg';
import './styles.css';
import ButtonIcon from 'components/ButtonIcon';
import { Link } from 'react-router-dom';

const Home = () => {
  return (
    <div className="home-container">
      <div className="base-card home-card">
        <div className="home-content-container">
          <div>
            <h1>Conheça nosso catálogo de produtos</h1>
            <p>Ajudaremos você a encontrar os nossos melhores produtos</p>
          </div>
          <div>
            <Link to="/products">
              <ButtonIcon text="Inicie agora sua busca agora"/>
            </Link>
          </div>
        </div>
        <div className="home-image-container">
          <MainImage />
        </div>
      </div>
    </div>
  );
};

export default Home;

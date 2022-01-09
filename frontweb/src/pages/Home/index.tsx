import Navbar from 'components/Navbar';
import { ReactComponent as MainImage } from 'assets/images/main-image.svg'
import './styles.css'
import ButtonIcon from 'components/ButtonIcon';

const Home = () => {
  return (
    <>
      <Navbar />
      <div className="home-container">
        <div className="home-card">
          <div className="home-content-container">
                <h1>Conheça nosso catálogo de produtos</h1>
                <p>Ajudaremos você a encontrar os nossos melhores produtos</p>
                <ButtonIcon/>
          </div>
          <div className="home-image-container">
                <MainImage/>
          </div>
        </div>
      </div>
    </>
  );
};

export default Home;

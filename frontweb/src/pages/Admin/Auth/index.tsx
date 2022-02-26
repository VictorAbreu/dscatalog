import { ReactComponent as AuthImage } from 'assets/images/auth-image.svg';

const Auth = () => {

    return(
        <div className="auth-container">
            <div className="auth-banner-container">
                <h1>Divulgue seus produtos no DS Catalog</h1>
                <p>Faça parte do nosso catálogo de divulgação e aumente a venda de seus produtos.</p>
                <AuthImage/>
            </div>
        </div>
    );
}

export default Auth;
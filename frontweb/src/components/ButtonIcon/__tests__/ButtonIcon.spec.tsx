import { render, screen } from "@testing-library/react";
import ButtonIcon from "..";


test('ButtonIcon deveria renderizar o botão com o texto informado', () => {

    const text = "Fazer login";

    render(
        <ButtonIcon text={text} />
    );
    screen.debug();

    expect(screen.getByText(text)).toBeInTheDocument();

})
import { render, screen } from "@testing-library/react";
import Pagination from "..";

describe('Testes de paginação', () => {

    // eslint-disable-next-line jest/valid-title
    test('Deve renderizar a paginação', () => {

        const pageCount = 3;
        const range = 3;
        render(
            <Pagination
                pageCount={pageCount}
                range={range}
            />
        );
        const page1 = screen.getByText("1")
        // eslint-disable-next-line @typescript-eslint/no-unused-expressions
        expect(page1).toBeInTheDocument();
        expect(page1).toHaveClass("pagination-link-active");
        expect(screen.getByText("2")).toBeInTheDocument();
        expect(screen.getByText("3")).toBeInTheDocument();
        expect(screen.queryByText("4")).not.toBeInTheDocument();
    });
});

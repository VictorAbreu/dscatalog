import { hasAnyRoles } from '../auth';
import * as TokenModule from '../token';

describe('hasAnyRoles tests', () => {

  test('deveria retornar verdadeiro quando a lista estiver vazia', () => {

    const result = hasAnyRoles([]);

    expect(result).toEqual(true);

  });


  test('deveria retornar verdadeiro quando o usuario possuir role', () => {

    jest.spyOn(TokenModule, 'getTokenData').mockReturnValue({

      exp: 0,
      user_name: '',
      authorities: ['ROLE_ADMIN', 'ROLE_OPERATOR']

    })

    const result = hasAnyRoles(['ROLE_ADMIN']);

    expect(result).toEqual(true);

  });

  test('deveria retornar falso quando o usuario não possuir role', () => {

    jest.spyOn(TokenModule, 'getTokenData').mockReturnValue({

      exp: 0,
      user_name: '',
      authorities: ['ROLE_OPERATOR']

    })

    const result = hasAnyRoles(['ROLE_ADMIN']);

    expect(result).toEqual(false);

  });

});
package com.ohgiraffers.section02.annotation.subsection01.primary;

import com.ohgiraffers.section02.annotation.common.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pokemonServicePrimary")
public class PokemonService {

    private Pokemon pokemon;

    // 이거 안 붙여도 생성자 주입 가능
    /* 설명. 생성자 위에 @Autowired 생략이 가능하다(default 생성자 주입) (feat. 웬만하면 명시하자) */
    // PokemonService의 기본 생성자가 없기 때문에 아래의 생성자가 기본으로 되어
    // 초기화를 시도하고 그 파라미터로 Pokemon 객체가 요구되기 때문에 bean 속에서 알아서 찾아다준다.
//    @Autowired
    public PokemonService(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public void pokemonAttack() {
        pokemon.attack();
    }
}

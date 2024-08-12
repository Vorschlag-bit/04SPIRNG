package com.ohgiraffers.section02.annotation.subsection04.resource;

import com.ohgiraffers.section02.annotation.common.Pokemon;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pokemonServiceResource")
public class PokemonService {
    //리소스는 필드만 주입 가능?

    // autowired랑 qualifier 합친 느낌
    /* 설명. Pokemon으로 처리 시 */
//    @Resource(name="charmander")
//    private Pokemon pokemon;

    /* 설명. List<Pokemon>으로 처리 시 */ // 리스트로 리소스 사용시 구체적 차이는? 필드 주입으로 가능하다?
    @Resource
    private List<Pokemon> pokemonList;

    public void pokemonAttack() {
//        pokemon.attack();

        pokemonList.forEach(Pokemon::attack);
    }
}

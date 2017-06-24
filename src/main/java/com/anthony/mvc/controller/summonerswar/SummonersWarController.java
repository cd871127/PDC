package com.anthony.mvc.controller.summonerswar;

import com.anthony.mvc.dto.summonerswar.MonsterFightTimeDTO;
import com.anthony.mvc.util.summonerswar.FightNum;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chend on 2017/4/16.
 */

@RestController("/summoner")
public class SummonersWarController {
    @CrossOrigin(origins = "http://localhost:3000", methods = RequestMethod.GET)
    @RequestMapping(value = "fighttime", method = RequestMethod.GET)
    public int calculateFightTimes(@RequestBody MonsterFightTimeDTO monsterFightTimeDTO, HttpServletRequest request) {

//        monsterFightTimeDTO=new MonsterFightTimeDTO();
//        monsterFightTimeDTO.setCurExp(0);
//        monsterFightTimeDTO.setCurLevel(1);
//        monsterFightTimeDTO.setStar(3);
//        monsterFightTimeDTO.setFightExp(2552);
        System.out.println(1);
        int res = FightNum.getFightNum(monsterFightTimeDTO);
        System.out.println(3);
        return res;
    }
}

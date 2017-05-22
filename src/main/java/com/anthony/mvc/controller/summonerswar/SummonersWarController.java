package com.anthony.mvc.controller.summonerswar;

import com.anthony.mvc.dto.summonerswar.MonsterFightTimeDTO;
import com.anthony.mvc.util.summonerswar.FightNum;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by chend on 2017/4/16.
 */

@RestController("/summoner")
public class SummonersWarController {
    @CrossOrigin(origins = "http://localhost:3000",methods = RequestMethod.POST)
    @RequestMapping(value = "fighttime",method = RequestMethod.POST)
    public int calculateFightTimes(@RequestBody MonsterFightTimeDTO monsterFightTimeDTO, HttpServletRequest request) {

//        monsterFightTimeDTO=new MonsterFightTimeDTO();
//        monsterFightTimeDTO.setCurExp(0);
//        monsterFightTimeDTO.setCurLevel(1);
//        monsterFightTimeDTO.setStar(3);
//        monsterFightTimeDTO.setFightExp(2552);
        int res = FightNum.getFightNum(monsterFightTimeDTO);
        System.out.println(3);
        return res;
    }
}

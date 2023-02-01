package com.hospital.cmn.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-cmn")
@Repository
public interface DictFeignClient {
    //    根据code和value查询名称
    @GetMapping("/admin/cmn/dict/getName/{value}/{dictCode}")
    public String getDictName(@PathVariable("value") Long value,
                              @PathVariable("dictCode") String dictCode);

    @GetMapping("/admin/cmn/dict/getName/{value}")
    public String getDictName(@PathVariable("value") Long value);

}

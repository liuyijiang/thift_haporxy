namespace java com.metoo.thift.service
namespace java com.metoo.thift.bean

struct PartsEntity{  
    1: string id,
    2: i32 like,  
    3: i64 comments,  
    4: string name  
}  

service PartsService{  
    list<PartsEntity> getPartsEntitys(1:string id),  
    bool savePartsEntity(1:PartsEntity entity),
    i32 getPort();	
}  
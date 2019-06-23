import re
input_text=r'{ 1 ,{10,{30,40}},{3,4,5}}'
delim=','
ignore_char=' ' #future support
class Node(object):
    def __init__(self,list):
        self.name=str(list[0])+'c'
        self.content=list
node_pool=[]
def changeBrace(pos):

    start_pos=pos
    res=[]
    # print(input_text[start_pos])
    i=pos
    while(i<len(input_text)):
        print('i='+str(i))
        if(input_text[i]==','):
            if (input_text[start_pos] != '{'):
                print(input_text[start_pos:i])
                node=int(input_text[start_pos:i])
                res.append(node)
            start_pos=i+1
        elif(input_text[i]=='{'):
            print('recursive')
            node,now_i=changeBrace(i + 1)

            res.append(node.name)
            i=now_i
        elif(input_text[i]=='}'):
            print(input_text[start_pos])
            if(input_text[start_pos]!='{'):
                node = int(input_text[start_pos:i])
                res.append(node)
                print('out'+str(i))

            node_pool.append(Node(res))
            return null,i
        i+=1
        node_pool.append(Node(res))

# print(int(input_text))
def main():
    nodee,no=changeBrace(1)
    node_pool.append(nodee)
    print(nodee.content)
    return nodee

if __name__ == '__main__':
    main()




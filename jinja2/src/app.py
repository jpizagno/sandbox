# -*- coding: utf-8 -*-
from pathlib import Path

from jinja2 import Environment, FileSystemLoader, select_autoescape

FILE_DIR = Path(__file__).parent
RES_DIR = FILE_DIR / '..' / 'results'
TPL_DIR = FILE_DIR / '..' / 'resources' / 'templates'

def main():


    user1 = { "username":"John", "url":"http://www.google.com" }
    user2 = { "username":"Jim", "url":"http://www.cnn.com" }
    users = [user1, user2]



    env = Environment(
        loader=FileSystemLoader(str(TPL_DIR)),
        autoescape=select_autoescape(['html'])
    )
    tpl = env.get_template('template.html')

    html = tpl.render(users=users)
    (RES_DIR / 'index.html').write_text(html)





if __name__ == '__main__':
    main()
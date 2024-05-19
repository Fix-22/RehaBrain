![RehaBrain logo](https://github.com/Fix-22/RehaBrain/blob/main/logo.png?raw=true)

# RehaBrain
A free cognitive stimulation therapy (CST) software made for people with neurodegenerative diseases.

## Goal
RehaBrain is developed to help people with neurodegenerative diseases using CST (cognitive stimulation therapy), trying to maintain their current cognitive abilities.
RehaBrain is developed to be **customizable** based on final user's interests and knowledge. At the moment images, words and categories of objects are a few but there will be more in the future.
The final goal is helping people who care relatives or friends with neurodegenerative diseases by providing them free CST tools.

## Why developing a new software?
There isn't much free CST software that can be **personalized** (based on person-specific criteria) on the internet. RehaBrain data is made to be fully customizable and it could be based on user's interests and knowledge.

## How it works
RehaBrain has **5** _(for now)_ different CST actvities, which are:
1. **Matching a given word to the corresponding object**
2. **Selecting all the images belonging a given specific category**
3. **Writing rhymes**
4. **Rebuilding a word by having its first and last letters**<br>
   It works like the hangman game, but the user has to write all the letters of a word, in the case of an error a letter, present in the word to rebuild, will be added.
5. **Sort all the steps of an action**<br>
   For example: given the action "brush my teeth", sort in the correct order the steps (which will be presented at random):
   1. Take the toothbrush
   2. Put on the toothbrush some toothpaste
   3. Brush teeths
   4. Rinse mouth
<br>
The user can modify which images and words will be displayed by RehaBrain by editing the files present in the selected profile.

### Creating profiles
RehaBrain has a default profile that shouldn't be modified.
To create a new profile just copy the directory `data/profiles/default` into `data/profiles` and rename it, then change the second line of `data/profiles/current.configuration` from `using default` to `using yournewprofilename`.<br>
Before doing it please read **carefully** the disclaimer.

## Language
Italian.

## Development
### Language and framework
RehaBrain is written in Java with the use of the [Processing](https://processing.org/) framework for GUI development.

## Credits
All the code is written by Simone Cecire.<br>
<br>
All the icons used for CST (present in `data/images` directory) created by [Freepik](https://www.flaticon.com/authors/freepik) - Flaticon<br>
<br>
Logo and window icon created by Simone Cecire.

## Disclaimer
**All the icons created by [Freepik](https://www.flaticon.com/authors/freepik) CAN'T be used outside RehaBrain, redistribuited or modified in any way. The author of RehabRain DOES NOT assume any responsibility for any violation of [Flaticon's license for Apps and games](https://support.flaticon.com/s/article/Apps-and-games-FI?language=en_US) made by the final user of RehaBrain.**
